class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var m_score1 = Score()
    private var m_score2 = Score()

    override fun wonPoint(playerName: String) {
        if (playerName === player1Name) {
            m_score1++
            return
        }
        m_score2++
    }

    override fun getScore(): String {
        TODO()
    }

}

data class Score(private var score: Int = 0) {
    operator fun inc() = apply { score++ }

    fun relativeTo(other: Score): String {
        if (this == other) {
            return parity()
        }
        if (m_score1 >= 4 || m_score2 >= 4) {
            return advantageOrWin()
        }
        return low()
    }

    private fun low() = "${lowScore(m_score1)}-${lowScore(m_score2)}"

    private fun advantageOrWin(): String {
        val difference = m_score1 - m_score2
        return when {
            difference == 1 -> "Advantage player1"
            difference == -1 -> "Advantage player2"
            difference >= 2 -> "Win for player1"
            else -> "Win for player2"
        }
    }

    private fun parity() = when (m_score1) {
        0 -> "Love-All"
        1 -> "Fifteen-All"
        2 -> "Thirty-All"
        else -> "Deuce"
    }

    private fun lowScore() = when (score) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        3 -> "Forty"
        else -> throw IllegalStateException("invalid score: $score")
    }
}
