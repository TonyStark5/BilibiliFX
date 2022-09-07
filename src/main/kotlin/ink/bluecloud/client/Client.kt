package ink.bluecloud.client

import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProvider

abstract class Client {
    abstract val apiProvider: NetWorkApiProvider
}