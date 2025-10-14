
```
██████╗ ██╗███╗   ██╗ █████╗ ██████╗ ██╗   ██╗██╗    ██╗ █████╗ ██╗   ██╗
██╔══██╗██║████╗  ██║██╔══██╗██╔══██╗╚██╗ ██╔╝██║    ██║██╔══██╗██║   ██║
██████╔╝██║██╔██╗ ██║███████║██████╔╝ ╚████╔╝ ██║ █╗ ██║███████║██║   ██║
██╔══██╗██║██║╚██╗██║██╔══██║██╔══██╗  ╚██╔╝  ██║███╗██║██╔══██║╚██╗ ██╔╝
██████╔╝██║██║ ╚████║██║  ██║██║  ██║   ██║   ╚███╔███╔╝██║  ██║ ╚████╔╝
╚═════╝ ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝    ╚══╝╚══╝ ╚═╝  ╚═╝  ╚═══╝
```
BinaryWav is cli project aiming to hidde binary file in to .wav and being able to recover them

## Installation
1. Download the latest [release]().
2. You'r good to go
### Or
1. Clone the repo 
```
git clone https://github.com/TheoBensaci/BinaryWav
```
2. In the repo, download the dependencyes
```
./mvnw dependency:go-offline
```
3. Build the .jar
```
./mvnw clean package 
```
You should have in the `./target` folder a file named `BinaryWav-X.X-jar-with-dependencies.jar`.

## Usage
Use the command
```
java -jar BinaryWav-X.X.jar
```
or if you compiled you self
```
java -jar target/BinaryWav-1.0.jar
```
to run the app.

With no argument, the app will run the help command
#### command
- `help` : show the help message of the app
- `hide` `source file path` `target file path` : hide `source file`  into a .wav `traget file`
- `get` `source file path` `target file path` : recover data from the .wav `source file` and put it in to `traget file`

## Authors
- [Theo Bensaci](https://github.com/TheoBensaci)
- [Gasmi Yasser](https://github.com/yss-g5)
## License
[MIT](https://choosealicense.com/licenses/mit/)