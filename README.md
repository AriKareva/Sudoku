Для успешной установки требуется немного: Terminal и внимательность

Шаг 1. Установка утилиты gradle@6 (довольно долго, >10 min) и настройка переменных
brew install gradle@6

2.2 Заходим в файл .zshrc с помощью команды
nano -w ~/.zshrc

2.3 В конец файла вставляем три строки ниже (перемещаемся в файле стрелочкой вниз)
export JAVA_HOME="$(brew --prefix openjdk@11)/libexec/openjdk.jdk/Contents/Home/"
export PATH="$(brew --prefix gradle@6)/bin:$PATH"
export PATH="$(brew --prefix openjdk@11)/bin:$PATH"
Последовательно нажимаем 1)Ctrl+X 2)y 3)Enter для сохранения изменений и выхода из файла

2.4 Прописываем в консоль это
source ~/.zshrc

Шаг 3. Установка приложения

3.1 Прописываем данные команды (лучше отдельно) для установки в папку на рабочий стол
cd ~/Desktop/
mkdir SudokuGame && cd $_

3.2 Клонируем репозиторий рамуса данной командой
git clone https://github.com/AriKareva/Sudoku

3.3 Заходим в папку

3.4 Запускаем сборку
gradle runLocal
