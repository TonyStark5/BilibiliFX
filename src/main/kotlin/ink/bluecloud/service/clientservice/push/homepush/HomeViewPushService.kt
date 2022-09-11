package ink.bluecloud.service.clientservice.push.homepush

import ink.bluecloud.model.data.HomePagePushCard
import ink.bluecloud.service.clientservice.push.PushService
import ink.bluecloud.service.provider.InjectByClassified
import ink.bluecloud.service.provider.ServiceType
import java.time.Duration
import java.util.*

@InjectByClassified(ServiceType.NetWork)
class HomeViewPushService: PushService() {
    fun getCard():HomePagePushCard {
        return HomePagePushCard(
            "一只小猫",
            "猫猫",
            Date(),
            Duration.ofMinutes(10),
            3022,
            1000,
            Thread.currentThread().contextClassLoader.getResourceAsStream("ui/homeview/demo.png").readAllBytes().inputStream()
        )
    }
}