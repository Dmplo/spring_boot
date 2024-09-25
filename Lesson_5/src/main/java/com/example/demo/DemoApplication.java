package com.example.demo;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        TaskRepository repository = ctx.getBean(TaskRepository.class);

        List<String> tasks = List.of("Обновить работу сайта", "Разработать программу запуска страницы", "Провести презентацию продукта", "Уменьшить норму отходов сырья в производственном цикле");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = new Task(getRandom(tasks));
            repository.save(task);
        }

    }

    private static <T> T getRandom(List<? extends T> list) {
        int index = ThreadLocalRandom.current().nextInt(0, list.size());
        return list.get(index);
    }

}
