import com.alibaba.fastjson.JSON
import java.time.LocalDate

fun main() {
    var jsonString = "{\"policyId\":10000,\"versionID\":1,\"policyName\":\"Seasonalcancellationpolicyoverridesbycheck-indates-nightly\",\"cancellationWindowMinCnt\":1680,\"externalCode\":\"10001\",\"skugroupId\":\"16639\",\"currency\":\"USD\",\"applyOnArrival\":true,\"applyOnDeposit\":false,\"defaultRules\":{\"targetDates\":{\"dateRanges\":[{\"startDate\":\"2022-06-01\",\"endDate\":\"2022-06-30\"}],\"dayOfWeek\":[\"DAY_OF_WEEK_SUNDAY\",\"DAY_OF_WEEK_MONDAY\",\"DAY_OF_WEEK_TUESDAY\",\"DAY_OF_WEEK_WEDNESDAY\",\"DAY_OF_WEEK_THURSDAY\",\"DAY_OF_WEEK_FRIDAY\",\"DAY_OF_WEEK_SATURDAY\"]},\"penaltyTiers\":[{\"window\":{\"unit\":\"HOUR\",\"value\":24},\"charges\":[{\"type\":\"PERCENT\",\"value\":0}]},{\"window\":{\"unit\":\"HOUR\",\"value\":0},\"charges\":[{\"type\":\"PERCENT\",\"value\":20}]}]},\"overrideRules\":[{\"targetDates\":{\"dateRanges\":[{\"startDate\":\"2022-06-01\",\"endDate\":\"2022-06-30\"}],\"dayOfWeek\":[\"DAY_OF_WEEK_SUNDAY\",\"DAY_OF_WEEK_MONDAY\",\"DAY_OF_WEEK_TUESDAY\",\"DAY_OF_WEEK_WEDNESDAY\",\"DAY_OF_WEEK_THURSDAY\",\"DAY_OF_WEEK_FRIDAY\",\"DAY_OF_WEEK_SATURDAY\"]},\"penaltyTiers\":[{\"window\":{\"unit\":\"HOUR\",\"value\":48},\"charges\":[{\"type\":\"PERCENT\",\"value\":20}]},{\"window\":{\"unit\":\"HOUR\",\"value\":0},\"charges\":[{\"type\":\"PERCENT\",\"value\":50}]}]}]}"
//    val cancellationPolicy: CancellationPolicy = JSON.parseObject(jsonString,CancellationPolicy::class.java)
    val cancellationPolicy2: CancellationPolicyConfiguration? = JSON.parseObject(jsonString,CancellationPolicyConfiguration::class.java)
    println(cancellationPolicy2.toString())


    val charges = setOf<CancellationCharge>(CancellationCharge(type = "FEE", value = 100, applicability = ""))
    val window = Window(unit = "HOUR", value = 24)
    val cancellationPenaltyTier = CancellationPenaltyTier(charges, window)

    val dateRange = DateRange("2022-06-30","2022-06-01")
    val targetDates = TargetDates(setOf(dateRange), setOf(DayOfWeek.DAY_OF_WEEK_SUNDAY, DayOfWeek.DAY_OF_WEEK_MONDAY))

    fun buildCustomizedCancellationRules(targetDates: TargetDates, setOfCancellationPenaltyTier: Set<CancellationPenaltyTier>): CancellationRules =
        CancellationRules.CustomizedCancellationRules(ruleVersionID = 10001, targetDates, setOfCancellationPenaltyTier)



    val cancellationRules: CancellationRules = buildCustomizedCancellationRules(targetDates, setOf(cancellationPenaltyTier))
    println("cancellationRules: ".plus(cancellationRules))
    fun buildCancellationPolicy(
        policyId: PolicyId = PolicyId(1234),
        policyName: PolicyName = PolicyName("Seasonalcancellationpolicyoverridesbycheck-indates-nightly"),
        applyOnArrival: ApplyOnArrival = ApplyOnArrival(true),
        applyOnDeposit: ApplyOnDeposit = ApplyOnDeposit(false),
        currency: Currency = Currency("USD"),
        externalCode: ExternalCode = ExternalCode("1212"),
        skugroupId: SkugroupId = SkugroupId("16639"),
        versionID: VersionID = VersionID(12),
        insertTime: LocalDate = LocalDate.now(),
        cancellationWindowMinCnt: CancellationWindowMinCnt = CancellationWindowMinCnt(10),
        cancellationRules: CancellationRules = buildCustomizedCancellationRules(targetDates, setOf(cancellationPenaltyTier))): CancellationPolicyConfiguration = CancellationPolicyConfiguration(policyId = policyId, policyName = policyName, applyOnArrival = applyOnArrival, applyOnDeposit = applyOnDeposit, defaultRules = cancellationRules, overrideRules = setOf(cancellationRules), cancellationWindowMinCnt = cancellationWindowMinCnt, currency = currency, externalCode = externalCode, skugroupId = skugroupId, versionID = versionID, insertTime = insertTime)

    val cancellationPolicy: CancellationPolicyConfiguration = buildCancellationPolicy()
    println("cancellationPolicy: ".plus(cancellationPolicy))


}