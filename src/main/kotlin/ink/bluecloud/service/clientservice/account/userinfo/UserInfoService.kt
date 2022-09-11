package ink.bluecloud.service.clientservice.account.userinfo

import ink.bluecloud.service.clientservice.account.AccountData
import ink.bluecloud.service.clientservice.account.AccountService

abstract class UserInfoService: AccountService() {
    abstract fun provideInfo(): AccountData
}