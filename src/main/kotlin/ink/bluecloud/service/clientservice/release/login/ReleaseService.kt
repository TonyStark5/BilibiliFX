package ink.bluecloud.ink.bluecloud.service.clientservice.release.login

import ink.bluecloud.client.HttpClient
import ink.bluecloud.service.provider.ClientService
import ink.bluecloud.service.provider.OnlyInjectList
import ink.bluecloud.service.provider.ServiceAutoRelease

@ServiceAutoRelease
@OnlyInjectList(HttpClient::class)
class ReleaseService: ClientService() {
    fun onExit() {
        httpClient.okHttpClient.dispatcher.executorService.shutdown()
    }
}