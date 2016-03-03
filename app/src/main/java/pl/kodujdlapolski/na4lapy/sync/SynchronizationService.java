package pl.kodujdlapolski.na4lapy.sync;

public interface SynchronizationService {

    String SYNCHRONIZATION_FINISHED_ACTION = "SynchronizationService.SYNCHRONIZATION_FINISHED_ACTION";
    String SYNCHRONIZATION_RESULT_KEY = "SynchronizationService.SYNCHRONIZATION_RESULT_KEY";

    void synchronize();
}
