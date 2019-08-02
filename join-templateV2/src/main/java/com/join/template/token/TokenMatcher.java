package com.join.template.token;

import com.join.template.matcher.MatchListener;

public interface TokenMatcher {

    boolean isMatch(String string, int current);

    int match(String string, int current, MatchListener matchListener);

    String getMatchBeginTag();

    String getMatchEndTag();
}
