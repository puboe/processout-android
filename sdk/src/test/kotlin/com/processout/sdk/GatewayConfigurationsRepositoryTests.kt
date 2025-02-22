package com.processout.sdk

import com.processout.sdk.api.ProcessOut
import com.processout.sdk.api.model.request.POAllGatewayConfigurationsRequest
import com.processout.sdk.api.model.request.POGatewayConfigurationRequest
import com.processout.sdk.api.repository.POGatewayConfigurationsRepository
import com.processout.sdk.config.SetupRule
import com.processout.sdk.config.TestApplication
import com.processout.sdk.config.assertFailure
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class GatewayConfigurationsRepositoryTests {

    @Rule
    @JvmField
    val setupRule = SetupRule()

    private lateinit var gatewayConfigurations: POGatewayConfigurationsRepository

    @Before
    fun setUp() {
        gatewayConfigurations = ProcessOut.instance.gatewayConfigurations
    }

    @Test
    fun fetch() = runBlocking {
        val request = POAllGatewayConfigurationsRequest(
            POAllGatewayConfigurationsRequest.Filter.NATIVE_ALTERNATIVE_PAYMENT_METHODS
        )
        gatewayConfigurations.fetch(request).assertFailure()
    }

    @Test
    fun find() = runBlocking {
        val request = POGatewayConfigurationRequest(
            "gway_conf_ux3ye8vh2c78c89s8ozp1f1ujixkl11k.adyenblik",
            withGateway = true
        )
        gatewayConfigurations.find(request).assertFailure()
    }
}
