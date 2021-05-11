<p align="center">
  <img src="https://safemoon.net/img/logov2.gif" width="64" />
  <br/>
  <h3 align="center">SafeMoon</h3>
</p>
<p align="center">
  <span align="center">MoonCraft Discord Bot 🚀</span>
  <br/>
  <a href ="https://safemoon.net/" target="_blank">https://safemoon.net/</a>
</p>

<p align="center">
  <a href="#about">About</a>
  •
  <a href="#commands">Commands</a>
  •
  <a href="#requirements">Requirements</a>
  •
  <a href="#installation">Installation</a>
  •
  <a href="#setup">Setup</a>
  •
  <a href="#license">License</a>
  •
  <a href="#contributors">Contributors</a>
</p>

---

## About

MoonCraft Bot is open source to allow our community to utilize, contribute and improve the experience
of the [SafeMoon Discord Server](https://discord.gg/safemoon).

Feel free to leave a ⭐ to help promote SafeMoon!

## Commands

_Substitute `!` in the below commands to be the prefix that is set in `config.json`._

| Command | Feature |
| ------- | ------- |
| **!!help**  | Lists all available commands |
| **!!online** | Lists all players currently online MoonCraft |
| **!!verify** | Links users' Minecraft and Discord accounts |

## Requirements

- Java JDK 11
- Gradle 6+

## Installation

```bash
$ git clone https://github.com/Safemoon-Protocol/MoonCraft-Bot.git
```

Once you've cloned the repository to your machine or server, you will need
to import it into your IDE of choice by importing the `build.gradle` file.
We recommend using IntelliJ for your IDE.

## Setup

Copy the `config.example.json` file within the `src\main\resources` directory, and rename it to `config.json`. You can then
update the values in the JSON file to your preference. The configuration file should look something like this:

```json
{
  "auth": "Backend API Auth Token",
  "token": "Discord Bot Token",
  "prefix": "!!"
}
```

To find your bot's token, go to the [Discord Developer Portal](https://discordapp.com/developers/applications/) and create
a bot. Copy the token that Discord gives you, and place this inside of your configuration JSON file. You will also need
to give your bot on the Developer Portal privileged gateway intents, you can find this on the Bot Settings on the portal.

| Configuration Setting | Information |
| --------------------- | ----------- |
| `auth` | Backend API authentication token |
| `token` | Your Discord bot's token |
| `prefix` | The identifier for commands |


#### Additional Information

In order to keep user information safe and secure, our backend servers are not available for public usage.

## License

Released under the [GNU GPL v3](https://www.gnu.org/licenses/gpl-3.0.en.html) license.

## Contributors

Find the [list of ✨ awesome ✨ contributors here](https://github.com/Safemoon-Protocol/MoonCraft-Bot/graphs/contributors).
Thank you to all who have contributed to the SafeMoon Discord Bot. 🚀
