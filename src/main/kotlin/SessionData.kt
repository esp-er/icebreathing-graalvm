
data class SessionData(val numBreaths: Int, val numRounds: Int, val breathHoldTime: Map<Int,Int>, val retentionType: RetentionType = RetentionType.CountUp)
