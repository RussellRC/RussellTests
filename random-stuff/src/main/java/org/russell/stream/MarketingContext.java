package org.russell.stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class MarketingContext {

    String segment;
    String geo;
    String format;

    public static MarketingContext newInstance(String segment, String geo, String format) {
        MarketingContext mc = new MarketingContext();
        mc.segment = segment;
        mc.geo = geo;
        mc.format = format;
        return mc;
    }

    @Override
    public String toString() {
        return String.format("{%s, %s, %s}", segment, geo, format);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(segment).append(geo).append(format).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MarketingContext)) {
            return false;
        }
        final MarketingContext mc = (MarketingContext) obj;
        if (!StringUtils.equals(segment, mc.segment)) {
            return false;
        }
        if (!StringUtils.equals(geo, mc.geo)) {
            return false;
        }
        return StringUtils.equals(format, mc.format);
    }
}
