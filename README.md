Bukkit
======

A Minecraft Server API.

Website: [http://bukkit.org](http://bukkit.org)
Bugs/Suggestions: [http://leaky.bukkit.org](http://leaky.bukkit.org)
Contributing Guidelines: [CONTRIBUTING.md](https://github.com/Bukkit/Bukkit/blob/master/CONTRIBUTING.md)

Projectstatus
------------

This repo is no more maintained.

There are various forks of Bukkit and CraftBukkit, and various opinions on how legal the CraftBukkit forks are in light of official statements from Mojang. Here's one relevant statement from Mojang:

>    Mojang has not authorized the inclusion of any of its proprietary
>    Minecraft software (including its Minecraft Server software) within the
>    Bukkit project to be included in or made subject to any GPL or LGPL
>    license, or indeed any other open source license

If you want a more modern, dev-friendly API that is unencumbered by the GPL licensing issues and even (optionally) supports running mods alongside plugins, you should check out the https://www.spongepowered.org project.

Compilation
-----------

We use maven to handle our dependencies.

* Install [Maven 3](http://maven.apache.org/download.html)
* Check out this repo and: `mvn clean install`
