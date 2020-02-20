package mobile_platforms

import enums.MobilePlatform

object MobilePlatformFactory {
    fun getMobilePlatform(platform: MobilePlatform) : AbstractPlatform =
        when(platform){
            MobilePlatform.ANDROID -> AndroidPlatform(platform)
            MobilePlatform.IOS -> IosPlatform(platform)
        }
}