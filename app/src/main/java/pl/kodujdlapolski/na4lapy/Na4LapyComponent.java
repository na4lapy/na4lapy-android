package pl.kodujdlapolski.na4lapy;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {Na4LapyModule.class})
public interface Na4LapyComponent {
}
