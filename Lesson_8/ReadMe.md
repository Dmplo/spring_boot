# Задание

1. Создать аспект, который аспектирует методы, помеченные аннотацией Recover, и делает следующее:
Если в процессе исполнения метода был exception (любой), то его нужно залогировать ("Recovering UserService#getUsers after Exception[RuntimeException.class, "exception message"]") и вернуть default-значение наружу Default-значение: для примитивов значение по умолчанию, для ссылочных типов - null.