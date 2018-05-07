package domain;

public class ApiRef {

    private String _self = "/accounts/id/{id}";
    private String _update = "/accounts/update/{id}";
    private String _followers = "/accounts/followers/{id}";
    private String _following = "/accounts/following/{id}";
    private String _kwets = "/kwets/owner/{id}/all";

    public ApiRef(long id) {
        _self = _self.replace("{id}", String.valueOf(id));
        _update = _update.replace("{id}", String.valueOf(id));
        _followers = _followers.replace("{id}", String.valueOf(id));
        _following = _following.replace("{id}", String.valueOf(id));
        _kwets = _kwets.replace("{id}", String.valueOf(id));
    }

    public String get_self() {
        return _self;
    }

    public void set_self(String _self) {
        this._self = _self;
    }

    public String get_update() {
        return _update;
    }

    public void set_update(String _update) {
        this._update = _update;
    }

    public String get_followers() {
        return _followers;
    }

    public void set_followers(String _followers) {
        this._followers = _followers;
    }

    public String get_following() {
        return _following;
    }

    public void set_following(String _following) {
        this._following = _following;
    }

    public String get_kwets() {
        return _kwets;
    }

    public void set_kwets(String _kwets) {
        this._kwets = _kwets;
    }
}
