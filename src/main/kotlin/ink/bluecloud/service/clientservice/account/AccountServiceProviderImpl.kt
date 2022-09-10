package ink.bluecloud.service.clientservice.account

/**
* 账户相关服务提供器
 *
* 通过本提供器，程序获得对用户账户的控制功能
* */
class AccountServiceProviderImpl: AccountServiceProvider() {
    override val accountData: AccountData
        get() = TODO("Not yet implemented")

    init {
//        localInjectArgs["accountData"] = accountData
    }
}