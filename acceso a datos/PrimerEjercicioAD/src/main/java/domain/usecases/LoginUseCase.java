package domain.usecases;

import domain.modelo.Reader;

public interface LoginUseCase {
    boolean doLogin(Reader reader);
}
