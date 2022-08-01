package persistence;

import org.json.JSONObject;

// Used code from the provided example
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
