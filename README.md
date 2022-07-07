# Rubato Composer

## Info

Rubato Composer is a music software based on the concepts and models of mathematical music theory.

Visit http://rubato.org and http://www.encyclospace.org for more information.

Former repository: https://sourceforge.net/projects/rubatocomposer/

## Install

Download the latest release [here](https://github.com/rubato-composer/rubato-composer/releases).

The `rubato-bin-YYYYMMDD.tar.gz` contains a Java archive `rubato.jar` which can be run by double-clicking it or by running
```bash
$ java -jar rubato.jar
```
in the console in the corresponding folder.

Alternatively, clone and build the src as subsequently described.

## Build

Clone the project, move to the folder, then install the dependencies to the local maven repository and run maven
```bash
$ sh install_libraries.sh
$ maven package
```
which generates the latest `jar` file in the target folder.


## List of known bugs

* **Rubato Composer GUI:** Sometimes dialog windows may become unresponsive to keyboard input. A slight resize of the window will normally make it responsive again. This seems happen on JDK 1.6.0.
* **Rubato Composer GUI, ScorePlayRubette:** There is a delay between the current position indicator and the playing.
