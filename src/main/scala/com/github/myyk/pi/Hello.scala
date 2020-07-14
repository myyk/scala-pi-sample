package com.github.myyk.pi

import com.github.myyk.pi.components.relay.MultiChannelRelay
import com.pi4j.io.i2c.I2CBus
import com.pi4j.util.Console

object Hello extends App {
  println("Hello, World!")

  val relay = MultiChannelRelay(I2CBus.BUS_1, false, false)

  val console = new Console
  console.println("Turn on Relay!")
  relay.turnOn()
  Thread.sleep(5000)
  console.println("Turn off Relay!")
  relay.turnOff()
}
