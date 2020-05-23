package com.cms.model;

public enum Verdict {
    STRONG_ACCEPT,
    ACCEPT,
    WEAK_ACCEPT,
    NOT_REVIEWED,
    WEAK_REJECT,
    REJECT,
    STRONG_REJECT;

    public static boolean all(Verdict verdict) {
        return true;
    }
    public static boolean accepted(Verdict verdict) {
        return STRONG_ACCEPT.equals(verdict) ||
                ACCEPT.equals(verdict) ||
                WEAK_ACCEPT.equals(verdict);
    }

    public static boolean rejectedOrNotReviewed(Verdict verdict) {
        return STRONG_REJECT.equals(verdict) ||
                REJECT.equals(verdict) ||
                WEAK_REJECT.equals(verdict) ||
                NOT_REVIEWED.equals(verdict);
    }

    public static boolean rejected(Verdict verdict) {
        return STRONG_REJECT.equals(verdict) ||
                REJECT.equals(verdict) ||
                WEAK_REJECT.equals(verdict);
    }

    public static boolean acceptedOrRejected(Verdict verdict) {
        return accepted(verdict) || rejected(verdict);
    }

}
