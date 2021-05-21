package bot;

import lombok.RequiredArgsConstructor;

/*
 * Model for config.json
 */

@RequiredArgsConstructor
public class Config {

    public final String token;
    public final String auth;
    public final String prefix;
    public final String presence;

}
