**Тестовое задание**

Необходимо реализовать мобильное приложение, использующее библиотеку, а именно:
1. Splash-экран. Экран показывается 3 секунды, в центре должен
располагаться логотип компании. Для логотипа можно взять любое
изображение, главное - отрисовка по центру.


2. Экран входа в приложение. Поле для ввода номера мобильного телефона и
кнопка "продолжить". Номер в формате +7 ХХХ ХХХ ХХ ХХ, необходимо
предусмотреть валидацию. Для регистрации использовать запрос на
регистрацию из библиотеки. Так же в запросе выставить флаг о том, что
пользователь согласен с офертой.


3. Экран ввода кода из смс. Поле ввода кода из смс и кнопка продолжить.
В случае успешной отправки запроса на регистрацию на указанный номер
будет отправлено смс сообщение с кодом. Данный код нужно отправить в
запросе на валидацию кода из смс. Предусмотреть валидацию на ввод
значения. Код всегда 6ти значный.

Предусмотреть обработку ошибок при проблемах с сетью.

**Описание приложения**

Это android - приложение для взаимодействия с сеорвером через APP API SDK.\
Использованный стек: Jetpack Compose, MVVM.\
Реализованы 3 экрана: SplashScreen (с помощью библиотеки google, поэтому анимация работает только с 12 версии android), экран  ввода номера телефона, экран ввода цифрового кода из sms и финальный экран сообщения об успешной авторизации.\
Выполнена валидация текстовых полей.


**Инструкция по сборке**

Шаг 1: На корневой странице этого репозитория ([https://github.com/AlekseyFokin/TestProjectSendy]) на середине страницы раскройте выпадающие меню на зеленой кнопке с надписью "<> Code" и скопируйте в буфер обмена url репозитория.

Шаг 2: Откройте Android Studio, затем перейдите в меню Файл > Создать > Проект из системы управления версиями.

Шаг 3: В поле Version control выберите Git из выпадающего меню.

Шаг 4: Вставьте url из буфера обмена в поле URL и выберите свой каталог, в котором будет размещена ваша копия проекта. Нажмите кнопку "Клонировать".

Шаг 5: В Android Studio запустите команду Build > Make Project. Дождитесь окончания подключения всех зависимостей.

Шаг 6: В Android Studio выберите конфигурацию запуска приложения (обычно app) или создайте свою. Произведите запуск приложения.
