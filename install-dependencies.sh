#!/bin/bash

# Run this from the Raspberry Pi after cloning this repo to get dependencies to
# to run the other sample code.

SCALA_VERSION=2.13.3

wget https://downloads.lightbend.com/scala/$SCALA_VERSION/scala-${SCALA_VERSION}.deb
sudo dpkg -i scala-$SCALA_VERSION.deb

# Note: If you are having error such as gpg: failed to start the dirmngr '/usr/bin/dirmngr': No such file or directory while adding the keyserver, then you should first install dirmngr by using sudo apt-get install dirmngr

# Install SBT (TODO: see if there's a simpler way)
sudo apt-get install apt-transport-https
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
sudo apt-get update
sudo apt-get install sbt

# Raspberry Pi has limited memory and JVM will consume a lot of memory if we don’t limit it. Therefore, we will modify the memory usage in SBT.
echo "Add this line on top of the file and save it:"
echo "   SBT_OPTS=-Xmx256M"
sudo nano /usr/bin/sbt

# For Relay example configure the I2C bus
echo "Follow these instructions to setup the I2C bus"
echo "https://wiki.52pi.com/index.php/DockerPi_4_Channel_Relay_SKU:_EP-0099#Configuring_I2C.28Raspberry_Pi.29"
