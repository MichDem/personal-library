package pl.miku.personallibrary.base;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class BaseRequest implements Serializable {
    public static class Id implements Serializable{
        @NotNull private Long id;

        public Long getId() {
            return id;
        }

        public Id setId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id1 = (Id) o;
            return Objects.equals(id, id1.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class GenId<T> implements Serializable {
        @NotNull
        private T id;

        public T getId() {
            return id;
        }

        public GenId<T> setId(T id) {
            this.id = id;
            return this;
        }
    }

    public static class GenValue<T> implements Serializable {
        @NotNull private T value;

        public T getValue() {
            return value;
        }

        public GenValue<T> setValue(T value) {
            this.value = value;
            return this;
        }
    }
}
