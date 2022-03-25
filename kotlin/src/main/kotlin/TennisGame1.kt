class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var playerOneScore = PlayerScore()
    private var playerTwoScore = PlayerScore()

    override fun wonPoint(playerName: String) {
        if (playerName === player1Name)
            playerOneScore++
        else
            playerTwoScore++
    }

    override fun getScore(): String {
        if (playerOneScore == playerTwoScore) {
            return parityScore()
        }
        if (playerOneScore.score >= 4 || playerTwoScore.score >= 4) {
            return advantageOrWinScore()
        }
        return loveToForty()
    }

    private fun loveToForty(): String {
        return "${scoreLabel(playerOneScore.score)}-${scoreLabel(playerTwoScore.score)}"
    }

    private fun scoreLabel(score: Int) = when (score) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        3 -> "Forty"
        else -> error("Invalid Score $score")
    }

    private fun advantageOrWinScore(): String {
        val minusResult = playerOneScore.score - playerTwoScore.score
        return when {
            minusResult == 1 -> "Advantage player1"
            minusResult == -1 -> "Advantage player2"
            minusResult >= 2 -> "Win for player1"
            else -> "Win for player2"
        }
    }

    private fun parityScore() = when (playerOneScore.score) {
        0 -> "Love-All"
        1 -> "Fifteen-All"
        2 -> "Thirty-All"
        else -> "Deuce"
    }
}

data class PlayerScore(var score: Int = 0) {
    operator fun inc(): PlayerScore {
        this.score++
        return this
    }
}
