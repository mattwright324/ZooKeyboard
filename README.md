# ZooKeyboard
Play random sound files on every key press.

## Features
* Plays any `wav` and `mp3` supplied at run time.
* Configure location of sound files.
* Press `F8` to quit the program.
* Add any supported audio file from the internet to play.
* Release comes with provided free/creative commons sound files from SoundBible.com and Freesound.org

## Setup
Unzip the latest release file into its own folder. Simply run `ZooKeyboard.jar` and start typing away at the keyboard!

## Configuration
Open `zookeyboard.json` in your favorite text editor and change the list of paths to point to your audio source folders.

    {"audio_sources": ["sound/default", "sound/explosions", "sound/naughty"]}

Full file paths are also supported.

    {"audio_sources": ["C:/My Sounds/Car Horns", "G:/My Sounds/Load Noises"]}