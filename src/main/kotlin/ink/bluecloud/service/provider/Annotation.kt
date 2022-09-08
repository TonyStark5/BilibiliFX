package ink.bluecloud.service.provider

import kotlin.reflect.KClass

/**
 * 通常情况下，当View中使用ProviderService时，系统会将对应的服务缓存起来供下次使用，这意味着
 * 该服务能将资源保存在自己的成员变量中而不被释放
 *
 * 在服务中提供此注解，在View使用该服务的时候将不会将其缓存，在调用结束后GC
 * 这意味着您需要更谨慎的保存资源
 */
@Target(AnnotationTarget.CLASS)
annotation class ServiceAutoRelease

/**
 * 并不是所有服务都需要用到所有依赖，通过这个注解，您可以通知注入器停止向您注入您不需要使用的资源
 * */
@Target(AnnotationTarget.CLASS)
annotation class ExcludeInjectList(vararg val clazz: KClass<*>)