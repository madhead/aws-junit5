tasks {
    val wrapper by creating(Wrapper::class) {
        gradleVersion = "4.10.2"
        distributionType = Wrapper.DistributionType.ALL
    }
}
