package ink.bluecloud.ui.homeview.fragment.sliderbar

import javafx.animation.Interpolator
import javafx.scene.effect.BlurType
import javafx.scene.effect.DropShadow
import tornadofx.*

val CLOUD_SHADOW = DropShadow(BlurType.GAUSSIAN, c("#E1ECFF"),10.0,0.0,1.0,3.0)
val CLOUD_INTERPOLATOR =  Interpolator.SPLINE(0.27, 0.75, 0.63, 1.0)