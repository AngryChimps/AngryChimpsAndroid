package com.angrychimps.citizenvet.models.receive;

public class SessionId {
    private Session session;

    public SessionId(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String get() {
        return session.getId();
    }

    public static class Session {
        private String id;

        public Session() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
