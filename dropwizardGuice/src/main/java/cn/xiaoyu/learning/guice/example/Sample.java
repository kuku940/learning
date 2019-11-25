package cn.xiaoyu.learning.guice.example;

import com.google.inject.*;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author roin.zhang
 * @date 2019/10/12
 */
interface IHelloPrinter {
    void print();
}

@Singleton
class SimpleHelloPrinter implements IHelloPrinter {
    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }
}

@Singleton
class ComplexHelloPrinter implements IHelloPrinter {
    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }
}

class SampleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IHelloPrinter.class).annotatedWith(Names.named("simple")).to(SimpleHelloPrinter.class);
        bind(IHelloPrinter.class).annotatedWith(Names.named("complex")).to(ComplexHelloPrinter.class);

        // 注入set集合
        Multibinder<IHelloPrinter> printers = Multibinder.newSetBinder(binder(), IHelloPrinter.class);
        printers.addBinding().to(SimpleHelloPrinter.class);
        printers.addBinding().to(ComplexHelloPrinter.class);

        // 注入Map集合
        MapBinder<String, IHelloPrinter> printerMap = MapBinder.newMapBinder(binder(), String.class, IHelloPrinter.class);
        printerMap.addBinding("simple").to(SimpleHelloPrinter.class);
        printerMap.addBinding("complex").to(ComplexHelloPrinter.class);
    }

    /**
     * 当对象很复杂时，可以通过@Providers方法来构造任意对象
     *
     * @return
     */
    @Provides
    @Singleton
    List<IHelloPrinter> providePrinterList() {
        List<IHelloPrinter> printerList = new ArrayList<>();
        printerList.add(new SimpleHelloPrinter());
        printerList.add(new ComplexHelloPrinter());
        return printerList;
    }
}

@Singleton
public class Sample {
    private IHelloPrinter simplePrinter;
    private IHelloPrinter complexPrinter;
    private Set<IHelloPrinter> printers;
    private Map<String, IHelloPrinter> printerMap;
    private List<IHelloPrinter> printerList;

    @Inject
    public Sample(@Named("simple") IHelloPrinter simplePrinter,
                  @Named("complex") IHelloPrinter complexPrinter,
                  Set<IHelloPrinter> printers,
                  Map<String, IHelloPrinter> printerMap,
                  List<IHelloPrinter> printerList) {
        this.simplePrinter = simplePrinter;
        this.complexPrinter = complexPrinter;
        this.printers = printers;
        this.printerMap = printerMap;
        this.printerList = printerList;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule());
        Sample sample = injector.getInstance(Sample.class);
        sample.hello();
    }

    public void hello() {
        simplePrinter.print();
        complexPrinter.print();

        // set集合
        printers.forEach(IHelloPrinter::print);
        // map集合
        printerMap.forEach((k, v) -> v.print());
        // provider提供的注入的List
        printerList.forEach(IHelloPrinter::print);
    }
}
