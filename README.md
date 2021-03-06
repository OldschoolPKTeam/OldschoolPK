![](https://i.imgur.com/jiO8DaG.png)
# OldschoolPK 

[![revision: 193][rev-badge]][patch]

OldschoolPK is a highly flexible, user-friendly game server based on <a href="https://github.com/bmyte/rsmod">a fork</a> of the original 
<a href="https://github.com/Tomm0017/rsmod">RS Mod</a>.
<br><a href="https://discord.gg/x3zQsGZrBS">Join us on Discord.</a>


## Installation

#### 1) Open IntelliJ
- If you do not have IntelliJ, you can download it from here: https://www.jetbrains.com/idea/download/other.html
- Rs Mod also uses Kotlin which is included by default in Intellij.

<!-- 
_Note| due to a regression in KotlinScript functionalities in the newest versions of the plugin it is <u>**imperative**</u> you install a version below 1.4.20!_ 
-->

#### 2) Import the project into IntelliJ

##### -- Using git Revision Control (Recommended)
- If you intend to make changes create a fork <a href="https://github.com/OldschoolPKTeam/OldschoolPK">from our git page</a>
- In your IntelliJ window, go to the top-left menu bar and navigate to ``File -> New -> Project from Version Control...``
- Enter the git repo URL (from your fork if you intend to contribute or from our repository if not)
    - this will clone the repository on your machine; if you're going to make contributions it is recommended to become familiar with aspects of git revision control

##### -- Downloading the source (for those who do not want to install git)

- Download this repository
- Extract the repository on your desktop (or directory of choice)
    - _Note| make sure you use ``Extract here``_
- In your IntelliJ window, go to the top-left menu bar and navigate to ``File -> New -> Project from Existing Sources...``
- In the ``Import Project`` window, select ``Import project from external model`` -> ``Gradle``
- In the next window you want to select the following and unselect anything else:
    - Select ``Create separate module per source set``
    - Select ``Use default gradle wrapper (recommended)``
    - In the ``Global Gradle settings`` section:
    - If ``Offline work`` is selected, unselect it
- Give the project a bit of time to create and index its files

<!-- #### 3) Install RS Mod
- On the top-right there should be a box ``Add Configuration...``, click on the box
- On the top-left of the ``Run/Debug Configurations`` window, click on the ``+`` button
- Select ``Gradle`` from the drop-down menu
- In the Unnamed Gradle task, you should now fill in the ``Configuration``
    - ``Gradle project`` click the folder button on its right side and select the ``:game`` option
    - ``Tasks`` set value to ``install``
    - ``Arguments`` set value to ```-x test```
- Now hit the ``Apply`` and then ``Ok`` button
- Next to the new button that should appear where the ``Add Configuration...`` was previously,
there should be a green ``run`` button, click on that and let the installation begin.
  -->
  
#### 3) RSA key setup
This step is only required if you're setting up your own client.

RSA is a method to stop man-in-the-middle (MITM) attacks on packets. RS Mod has this method enabled by default,
no two servers should use the same private key. We have default RSA keys set for development, you can skip to 
``Setup the client to use your public key`` if you are developing locally. Otherwise you should generate new RSA keys.

- Generate RSA keys (not required for local development)
    - Run ``gradle install`` to generate RSA keys.
    - After the ``install`` task completes, it will print out a message on the IntelliJ console
    - Once your key is created, you will have to follow the instructions on the terminal/command prompt
- Setup the client to use your public key
    - You need to copy the public key you are given and replace those in your client
    - In your client, you can find the text `BigInteger("10001")` which will usually be found with the variable you need to replace with the RSA public key.

#### 4) Setup the revision

_Note| As indicated by the revision badge at the top of this readme, the master branch of this repo is currently on ``193``, and you should always work to try and match client and cache when catering to a specific revision. Also a fair amount of work is required to change revisions as the protocols and interfaces change from revision to revision._

- Create the folder path ``${rsmod-project}/data/cache/cache``
- Go to the archives page and select the revision you want your server to run on: https://archive.runestats.com/osrs/
- Download whichever archive you want
- Open your downloaded archive, and copy the files in its "cache" folder and place them in your RS Mod folder you just created ``${rsmod-project}/data/cache/cache/``
- Create the folder ``${rsmod-project}/data/cache/xteas/``
- Copy the file ``xteas.json`` from the downloaded archive and place it in the ``xteas`` folder you just created

#### 5) Run the Server
- On the top-right click on the configurations box and select ``Edit Configurations...``
- On the top-left of the ``Run/Debug Configurations`` window, click on the ``+`` button
- Select ``Gradle`` from the drop-down menu
- In the Unnamed Gradle task, you should now fill in the ``Configuration``
    - ``Gradle project`` click the folder button on its right side and select the ``:game`` option
    - ``Tasks`` set value to ``run``
- Now hit the ``Apply`` and then ``Ok`` button
- Next to the new button that should appear where the other configuration was previously,
there should be a green ``run`` button, click on that and the server should begin to run.

## Installing Plugins
To install all publicly available (and any other plugins you have access to) you need to run the ``gradle git-clone`` task which will clone each individual plugin.

## Creating Plugins
##### Creating the plugin module
- Make sure you've installed the existing plugins first.
- Select a name for your plugin. The name has to be unique to all other plugins, otherwise you will receive a build error.
- Find the folder path `/game/plugins/content` or `/game/plugins/core` depending
on the type of plugin you're adding.
- Create a new folder inside the path with the chosen name of your plugin
(ex. `/game/plugins/content/npcs/vorkath`). Your plugin cannot be nested within another plugin,
a new folder must be used.
- Add a `build.gradle.kts` (preferred) or `build.gradle` file inside (ex. `/game/plugins/content/your-plugin/build.gradle.kts`).
- Add a `/src/main/kotlin/` folder inside (ex. `/game/plugins/content/your-plugin/src/main/kotlin`).
- Refresh gradle and it will be automatically added as a module.

##### Adding source code
- All source code should be Kotlin and stored inside a `your-plugin/src/main/kotlin/` folder.
- Any source files should be packaged according to it's location within the file structure 
(e.g. a plugin `/game/plugins/content/your-plugin/`, containing the kotlin file `plugin.kts` 
should have a location of `/your-plugin/src/main/kotlin/gg/rsmod/plugins/content/yourplugin/plugin.kts` and have the package 
`gg.rsmod.plugins.content.yourplugin`).

- You can look in [our discord](https://discord.gg/x3zQsGZrBS) for individual tutorials on how to add new content.

##### Using resource files
- Resources are external files that are consumed inside your plugins (text, images, json).
- Resources should be contained inside a folder called resources (`/your-plugin/src/main/resources/`).
- All resources should have a package that matches the file in which they are consumed.
- You can use the `getPluginResourcePackaged()` extension function to easily consume resources a class.

##### Sharing your plugin
- If you want your plugin to be added to the main repository:
    - Your plugin module should be a public git repository.
    - Add your plugin to the `/game/plugins/build.gradle.kts` file with the other plugins.
    - Submit a pull request on this repository with your addition.
- If you want to share your plugin with friends or with the community:
    - You can use the method above and share a repository or distribute it as a zip.


## Troubleshooting
- *Where can I get a client?*
    - You can ask on the discord for a client or you can get a client from the same archive file you download for the cache but will require decompiling the `deob-gamepack.jar` into useable source in order to set the RSA and hostname for connecting to your server.
- *I receive a* ``Bad session id`` *message on the log-in screen*
    - This means the RSA keys on the client do not match the ones created on the server.
    You should try to follow the steps in ``4) RSA key setup`` again.
- *I receive a* ``Revision mismatch for channel`` *console message when trying to log in*
    - Your client revision must match the one indicated in the ``${rsmod-project}/game.yml`` configuration file
    - This repo is currently set for ``revision: 193`` so match your client's revision to that.
- *I receive a* ``error_game_js5connect`` *error on the client console*
    - You need to launch the server first and *then* the client
- I receive a `java.lang.NoClassDefFoundError: Could not initialize class class_name_here` when trying to log into the client
    - The RSA key you copied to the client should not include a newline (`\n`) at the end - remove it.

## FAQ

#### I would like a feature added to the core game module
- If you would like a feature added, you can create a pull request on GitHub or ask the maintainers to add the feature.

#### I found a bug, where can I report it?
- You can report them as issues on GitHub or on [our discord](https://discord.gg/x3zQsGZrBS).

## Acknowledgments
### RS Mod
* ##### Tomm
    - For all his hard work on [RS Mod](https://github.com/Tomm0017/rsmod/)
    - His dedication to the OSRSPS community and producing a quality Open Source emulation server for it
* ##### [![](https://jitpack.io/v/runelite/runelite.svg)](https://jitpack.io/#runelite/runelite)
    - Using Cache module from RuneLite
* ##### Graham Edgecombe
    - Using project structure based on Apollo
    - Using StatefulFrameDecoder, AccessMode, DataConstants, DataOrder, DataTransformation, DataType, GamePacket & GamePacketBuilder from Apollo
* ##### Major
    - Using Collision Detection from Apollo
    - Basic idea behind the Region system (known as Chunk on RS Mod)
* ##### Polar
    - Helpful lad who shares a lot of OSRS knowledge
    - Useful OSRS archives https://archive.runestats.com/osrs/
    - Conversations of how to improve RS Mod
* ##### Sini
    - Advice on improving RS Mod's infrastructure
* ##### Kris
    - Always willing to lend a hand and share some code
* ##### Bart and Scu11
    - Helped solve an issue with setting up KotlinScript
* ##### Bart (from original OSS team)
    - The basic idea behind certain features such as TimerSystem, AttributeSystem and Services
    - The basic idea behind user-friendly plugin binding
* ##### Rune-Status
    - Discord members lending a hand and being helpful
    
### OldschoolPK
* ##### Bmyte
    - For converting RSMod to 193 and helping us along our journey
* ##### Kobra
    - For his many contributions and being a good friend along the way

[patch]: https://oldschool.runescape.wiki/w/2020_Christmas_event
[rev-badge]: https://img.shields.io/badge/revision-193-success

<p>If you're interested in the development side you should also check out <a href="https://github.com/rsmod/rsmod">RS Mod v2.0</a>, which is an improved version of the original RS Mod.</p>
