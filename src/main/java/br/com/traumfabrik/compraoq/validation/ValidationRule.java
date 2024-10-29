package br.com.traumfabrik.compraoq.validation;

public interface ValidationRule<T> {
    public void validate(T obj);
}
