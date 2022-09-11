package ink.bluecloud.service.clientservice.release.releaseOkhttp

import ink.bluecloud.client.HttpClient
import ink.bluecloud.service.ClientService
import ink.bluecloud.service.provider.InjectListOnly
import ink.bluecloud.service.provider.ServiceAutoRelease

@ServiceAutoRelease
@InjectListOnly(HttpClient::class)
class ReleaseService: ClientService() {
    fun onExit() {
        httpClient.okHttpClient.dispatcher.executorService.shutdown()
    }
}