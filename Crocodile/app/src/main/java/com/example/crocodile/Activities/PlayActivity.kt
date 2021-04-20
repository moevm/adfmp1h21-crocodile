package com.example.crocodile.Activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.crocodile.Helpers.CurrentGameStatsHelper
import com.example.crocodile.Helpers.OptionsHelper
import com.example.crocodile.Helpers.SharedPreferenceHelper
import com.example.crocodile.Helpers.WordsHelper
import com.example.crocodile.Listeners.OnSwipeTouchListener
import com.example.crocodile.R
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit


class PlayActivity : AppCompatActivity() {

    private lateinit var firstCard: CardView
    private lateinit var secondCard: CardView
    private lateinit var thirdCard: CardView
    private lateinit var fourthCard: CardView
    private lateinit var fifthCard: CardView

    private lateinit var cardsArray: Array<CardView>
    private lateinit var scoreLabel: TextView

    private lateinit var guessLabel: TextView
    private lateinit var missLabel: TextView

    private lateinit var cardLayout: LinearLayout

    private var score = 0
    private var resumeFromMillis = 0L
    private var isPaused = false
    private var isFirstOpen = true

    private lateinit var timeLabel: TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val teamNameLabel: TextView = findViewById(R.id.play_team_label)
        teamNameLabel.text = CurrentGameStatsHelper.getCurrentTeamName(this)

        cardLayout = findViewById(R.id.cardLayout)

        guessLabel = findViewById(R.id.guessed)
        missLabel = findViewById(R.id.missed)

        timeLabel = findViewById(R.id.time)
        val timerLong = OptionsHelper.getTimesForRoundPref(this)
        timer((timerLong*1000).toLong(), 1000).start()

        val parentLinear: LinearLayout = findViewById(R.id.parentLinear)
        scoreLabel = findViewById(R.id.score_team)

        firstCard = findViewById(R.id.card)
        firstCard.setOnTouchListener(object: OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                animateSwipe(parentLinear, firstCard, false)
            }
            override fun onSwipeRight() {
                animateSwipe(parentLinear, firstCard, true)
            }
        })

        secondCard = findViewById(R.id.cardSecond)
        secondCard.setOnTouchListener(object: OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                animateSwipe(parentLinear, secondCard, false)
            }
            override fun onSwipeRight() {
                animateSwipe(parentLinear, secondCard, true)
            }
        })

        thirdCard = findViewById(R.id.cardThird)
        thirdCard.setOnTouchListener(object: OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                animateSwipe(parentLinear, thirdCard, false)
            }
            override fun onSwipeRight() {
                animateSwipe(parentLinear, thirdCard, true)
            }
        })

        fourthCard = findViewById(R.id.cardFourth)
        fourthCard.setOnTouchListener(object: OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                animateSwipe(parentLinear, fourthCard, false)
            }
            override fun onSwipeRight() {
                animateSwipe(parentLinear, fourthCard, true)
            }
        })

        fifthCard = findViewById(R.id.cardFifth)
        fifthCard.setOnTouchListener(object: OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                animateSwipe(parentLinear, fifthCard, false)
            }
            override fun onSwipeRight() {
                animateSwipe(parentLinear, fifthCard, true)
            }
        })

        cardsArray = arrayOf(firstCard, secondCard, thirdCard, fourthCard, fifthCard)

        updateWords()
    }

    override fun onStop() {
        super.onStop()

        pauseTimer()
    }

    override fun onResume() {
        super.onResume()

        if (!isFirstOpen) {
            resumeTimer()
        }
        isFirstOpen = false
    }

    fun rightTimer(millisUntilFinished: Long): String {
        val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
        val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
        return String.format("%02d:%02d", minutes, seconds)
    }

    fun animateSwipe(parentView: LinearLayout, view: CardView, isRight: Boolean) {
        view.visibility = View.VISIBLE
        view.alpha = 1.0f

        calcScore(isRight, view)

        scoreLabel.text = "$score очков"

        val xOffset = xOffset(parentView)
        view.animate()
            .translationX(if(isRight) xOffset else -xOffset)
            .alpha(0.0f)
            .withEndAction {
                if (isCardsAreTransparent(cardsArray)) {
                    cardFlipAnimation()
                }
            }

        val animationView = if (isRight) missLabel else guessLabel
        pulseAnimation(animationView)
    }

    fun isCardsAreTransparent(cardsArray: Array<CardView>) : Boolean {
        cardsArray.iterator().forEach {
            if (it.alpha == 0.0f) {}
            else return false
        }
        return true
    }

    fun calcScore(isRight: Boolean, view: CardView) {
        val answerPrice = getAnswerPrice(view)
        var appendingScore = if(isRight) -answerPrice else answerPrice
        if (appendingScore < 0 && !OptionsHelper.getSkipSwitchPref(this) ) {
            appendingScore = 0
        }
        score += appendingScore
        if (score < 0) { score = 0 }
    }

    fun getAnswerPrice(view: CardView): Int {
        return when(view) {
            firstCard -> 5
            secondCard -> 4
            thirdCard -> 3
            fourthCard -> 2
            fifthCard -> 1
            else -> {
                1
            }
        }
    }

    private fun xOffset(parentView: LinearLayout): Float {
        return (parentView.width / 2).toFloat()
    }

    fun animateSwipeBack(view: CardView) {
        view.visibility = View.VISIBLE
        view.alpha = 0.0f

        view.animate()
                .translationX(0.0f)
                .alpha(1.0f)
                .setListener(null)
    }

    private fun updateWords() {
        val firstWord: TextView = findViewById(R.id.word)
        firstWord.text = "+5  " + WordsHelper.getRandomHardWord()

        val secondWord: TextView = findViewById(R.id.wordSecond)
        secondWord.text = "+4  " + WordsHelper.getRandomMediumWord()

        val thirdWord: TextView = findViewById(R.id.wordThird)
        thirdWord.text = "+3  " + WordsHelper.getRandomMediumWord()

        val fourthWord: TextView = findViewById(R.id.wordFourth)
        fourthWord.text = "+2  " + WordsHelper.getRandomEasyWord()

        val fifthWord: TextView = findViewById(R.id.wordFifth)
        fifthWord.text = "+1  " + WordsHelper.getRandomEasyWord()
    }

    private fun cardFlipAnimation () {
        val oa1 = ObjectAnimator.ofFloat(cardLayout, "scaleX", 1f, 0f)
        val oa2 = ObjectAnimator.ofFloat(cardLayout, "scaleX", 0f, 1f)
        oa1.interpolator = DecelerateInterpolator()
        oa2.interpolator = AccelerateDecelerateInterpolator()
        oa1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                oa2.start()
                for (card in cardsArray) {
                    animateSwipeBack(card)
                }
                updateWords()
            }
        })
        oa1.start()
    }

    private fun pulseAnimation(view: TextView) {
        view.visibility = View.VISIBLE
        view.alpha = 1.0f
        view.animate()
                .alpha(0.0f)
                .withEndAction {
                    view.animate().alpha(1.0f)
                }
    }

    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
                if (isPaused){
                    resumeFromMillis = millisUntilFinished
                    cancel()
                } else {
                    timeLabel.text = rightTimer(millisUntilFinished)
                }
            }

            override fun onFinish() {
                CurrentGameStatsHelper.saveCurrentTeamScore(this@PlayActivity, score)
                val currentTeamScore = CurrentGameStatsHelper.getCurrentTeamScore(this@PlayActivity)
                val anotherTeamScore = CurrentGameStatsHelper.getAnotherTeamScore(this@PlayActivity)
                if (CurrentGameStatsHelper.getIsGameCanFinish(this@PlayActivity)
                    && (currentTeamScore >= OptionsHelper.getWordsToWinPref(this@PlayActivity)
                            || anotherTeamScore >= OptionsHelper.getWordsToWinPref(this@PlayActivity))
                    && currentTeamScore != anotherTeamScore) {
                    if (anotherTeamScore > currentTeamScore) {
                        CurrentGameStatsHelper.swapCurrentTeamQueue(this@PlayActivity)
                    }
                    finishAffinity()
                    val intent = Intent(this@PlayActivity, ResultActivity::class.java)
                    startActivity(intent)
                } else {
                    CurrentGameStatsHelper.saveIncreasedRoundNumber(this@PlayActivity)
                    CurrentGameStatsHelper.swapCurrentTeamQueue(this@PlayActivity)
                    finish()
                }
            }
        }

    }

    private fun pauseTimer() {
        isPaused = true
    }

    private fun resumeTimer() {
        timer(resumeFromMillis+1000,1000).start()
        isPaused = false
    }

    override fun onBackPressed() {}
}