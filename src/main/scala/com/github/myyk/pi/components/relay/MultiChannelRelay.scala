package com.github.myyk.pi.components.relay

import com.pi4j.io.i2c.I2CFactory

// This code should work with this relay.
// SKU: EP-0099
// Name: DockerPi 4 Channel Relay
// Docs: https://wiki.52pi.com/index.php/DockerPi_4_Channel_Relay_SKU:_EP-0099#Program_in_Java.28Raspberry_Pi.29
// Shop link: https://www.banggood.com/4-Channel-Relay-HAT-Module-Board-For-Raspberry-Pi-3B3BPlus-p-1479237.html

object MultiChannelRelay {
  // channel of relay.
  val DOCKER_PI_RELAY_1 = 0x01.toByte
  val DOCKER_PI_RELAY_2 = 0x02.toByte
  val DOCKER_PI_RELAY_3 = 0x03.toByte
  val DOCKER_PI_RELAY_4 = 0x04.toByte
  // Relay status
  val DOCKER_PI_RELAY_ON = 0xFF.toByte
  val DOCKER_PI_RELAY_OFF = 0x00.toByte

  def apply(bus: Int, dip1: Boolean, dip2: Boolean): MultiChannelRelay = {
    val address = (dip1, dip2) match {
      case (false, false) => 0x01
      case (false, true) => 0x11
      case (true, false) => 0x12
      case (true, true) => 0x13
    }
    new MultiChannelRelay(bus, address)
  }
}

class MultiChannelRelay(val bus: Int, val address: Int) {
  var isOn = false

  val i2c = I2CFactory.getInstance(bus)
  val device = i2c.getDevice(address)

  import MultiChannelRelay._

  def turnOn(): Unit = {
    device.write(DOCKER_PI_RELAY_1, DOCKER_PI_RELAY_ON)
  }

  def turnOff(): Unit = {
    device.write(DOCKER_PI_RELAY_1, DOCKER_PI_RELAY_OFF)
  }
}

