package com.join.template.matcher;

import com.join.template.token.TokenMatcher;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public class DefaultTokenMatcher implements TokenMatcher {


    protected String matchBeginTag;

    protected String matchEndTag;

    @Override
    public boolean isMatch(String string, int current) {
        return string.startsWith(matchBeginTag, current);
    }

    @Override
    public int match(String string, int current, MatchListener matchListener) {
        int index = string.indexOf(matchEndTag, current);
        if (matchListener != null) {
            String token = string.substring(current + matchBeginTag.length(), index);
            matchListener.match(token);
        }
        return index + matchEndTag.length();
    }

    @Override
    public String getMatchBeginTag() {
        return matchBeginTag;
    }

    public void setMatchBeginTag(String matchBeginTag) {
        this.matchBeginTag = matchBeginTag;
    }
    @Override
    public String getMatchEndTag() {
        return matchEndTag;
    }

    public void setMatchEndTag(String matchEndTag) {
        this.matchEndTag = matchEndTag;
    }

}
