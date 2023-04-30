package de.chloedev.dcbridge.shared;

public record KeyValuePair<K, V>(K key, V value) {

    public static KeyValuePair<String, String> getStringPair(String message) {
        return new KeyValuePair<>(message.split(";;")[0], message.split(";;")[1]);
    }

    @Override
    public String toString() {
        return key + ";;" + value;
    }
}
