package pl.kodujdlapolski.na4lapy.sync;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.api.ApiService;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;

public class SynchronizationServiceImpl implements SynchronizationService {

    private ApiService mApiService;
    private DatabaseRepository mDatabaseRepository;

    @Inject
    public SynchronizationServiceImpl(ApiService apiService, DatabaseRepository databaseRepository) {
        mApiService = apiService;
        mDatabaseRepository = databaseRepository;
    }

    @Override
    public void synchronize() {

    }
}
