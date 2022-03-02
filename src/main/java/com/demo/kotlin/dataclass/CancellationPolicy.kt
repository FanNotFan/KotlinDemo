import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

@JvmInline
value class PolicyId(val value: Int)
@JvmInline
value class PolicyName(val value: String)
@JvmInline
value class ApplyOnArrival(val value: Boolean)
@JvmInline
value class ApplyOnDeposit(val value: Boolean)
@JvmInline
value class CancellationWindowMinCnt(val value: Int)
@JvmInline
value class Currency(val value: String)
@JvmInline
value class ExternalCode(val value: String)
@JvmInline
value class SkugroupId(val value: String)
@JvmInline
value class VersionID(val value: Int)

data class CancellationPolicyConfiguration(
    @field:NotNull
    val policyId: PolicyId,
    @field:NotNull
    val policyName: PolicyName,
    @field:NotNull
    val applyOnArrival: ApplyOnArrival,
    @field:NotNull
    val applyOnDeposit: ApplyOnDeposit,
    val defaultRules: CancellationRules,
    val overrideRules: Set<CancellationRules>,
    val cancellationWindowMinCnt: CancellationWindowMinCnt,
    @field:NotNull
    val currency: Currency,
    @field:NotNull
    val externalCode: ExternalCode,
    @field:NotNull
    val skugroupId: SkugroupId,
    @field:NotNull
    val versionID: VersionID,
    @field:PastOrPresent
    val insertTime: LocalDate
)

sealed class CancellationRules {
    abstract val ruleVersionID: Int
    abstract val targetDates: TargetDates

    data class CustomizedCancellationRules(
        override val ruleVersionID: Int,
        override val targetDates: TargetDates,
        val penaltyTiers: Set<CancellationPenaltyTier>
    ) : CancellationRules()

    data class PredefinedCancellationRules(
        override val ruleVersionID: Int,
        override val targetDates: TargetDates,
        val predefinedCancellationRuleName: PredefinedCancellationRuleName
    ) : CancellationRules()
}

data class CancellationPenaltyTier(
    val charges: Set<CancellationCharge>,
    val window: Window
)

data class TargetDates(
    val dateRanges: Set<DateRange>,
    val dayOfWeek: Set<DayOfWeek>
)

data class CancellationCharge(
    val type: String,
    val value: Int,
    val applicability: String
)

data class Window(
    val unit: String,
    val value: Int
)

data class DateRange(
    val endDate: String,
    val startDate: String
)

enum class DayOfWeek(val value: String, val dayOfWeekNumber: Byte) {
    DAY_OF_WEEK_SUNDAY("SUN", 0),
    DAY_OF_WEEK_MONDAY("MON", 1),
    DAY_OF_WEEK_TUESDAY("TUE", 2),
    DAY_OF_WEEK_WEDNESDAY("WED", 3),
    DAY_OF_WEEK_THURSDAY("THU", 4),
    DAY_OF_WEEK_FRIDAY("FRI", 5),
    DAY_OF_WEEK_SATURDAY("SAT", 6);
}

enum class PredefinedCancellationRuleName(val ruleName: String) {
    RELAXED("Relaxed"),
    MODERATE("Moderate"),
    FIRM("Firm"),
    STRICT("Strict"),
    NONE("None")
}