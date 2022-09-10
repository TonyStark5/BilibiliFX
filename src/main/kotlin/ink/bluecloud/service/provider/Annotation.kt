package ink.bluecloud.service.provider

import ink.bluecloud.ink.bluecloud.service.provider.ServiceType
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
annotation class InjectListExcluding(vararg val clazz: KClass<*>)

/**
 * 并不是所有服务都需要用到所有依赖，通过这个注解，您可以通知注入器仅向您的服务注入部分资源
 * */
@Target(AnnotationTarget.CLASS)
annotation class InjectListOnly(vararg val clazz: KClass<*>)

/**
 * 并不是所有服务都需要用到所有依赖，通过这个注解，您可以注入所有依赖项
 * */
@Target(AnnotationTarget.CLASS)
annotation class InjectAllResource

/**
 * 并不是所有服务都需要用到所有依赖，通过这个注解，您可以按照分类注入您需要的依赖项目
 * */
@Target(AnnotationTarget.CLASS)
annotation class InjectByClassified(vararg val type: ServiceType)

/**
 * 通过这个注解标记该依赖的作用类型
 * */
@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE)
annotation class InjectResourcesType(val type: ServiceType)