import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import lombok.SneakyThrows;

public class Berries {


    @SneakyThrows
    public static void main(String[] args) {
        Supplier<String> latestJdkVersionCache = Suppliers.memoizeWithExpiration(() -> "Tung", 5, TimeUnit.SECONDS);
        System.out.println(latestJdkVersionCache.get());
        System.out.println(latestJdkVersionCache.get());
        System.out.println(latestJdkVersionCache.get());

        TimeUnit.SECONDS.sleep(10);
        System.out.println();
        System.out.println(latestJdkVersionCache.get());

    }

}
