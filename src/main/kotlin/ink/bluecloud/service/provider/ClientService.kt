package ink.bluecloud.service.provider

import ink.bluecloud.client.HttpClient
import ink.bluecloud.model.networkapi.NetWorkApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
* 服务核心类
 *
 * 任何一个服务都通过这个类初始化，这意味着当您属于本类的子类时
 * 您将获得本类的所有资源
 * 如果您不需要使用某个资源，请使用ExcludeInjectList注解排除您不需要的依赖
 * 当您使用该注解停止注入操作后，任何使用该变量的位置都会抛出UninitializedPropertyAccessException
* */
abstract class ClientService {
    //网络访问资源
    protected lateinit var httpClient: HttpClient
    protected lateinit var netWorkApiProvider: NetWorkApiProvider

    //协程控制资源
    private lateinit var ioScope: CoroutineScope
    private lateinit var uiScope: CoroutineScope

    /**
    * 运行在JavaFx协程上
    * */
    protected fun UI(block: suspend CoroutineScope.() -> Unit) {
        uiScope.launch {
            block()
        }
    }

    /**
     * 运行在IO协程上
     * */
    protected fun IO(block: suspend CoroutineScope.() -> Unit) {
        ioScope.launch {
            block()
        }
    }
}