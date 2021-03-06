package com.java.hibernate.core.dataAccessObject;


import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


@javax.faces.bean.ManagedBean
@ApplicationScoped

public class DateRangeFilter implements Serializable
{

    private static final Logger LOG = Logger.getLogger(DateRangeFilter.class.getName());

    public boolean filterByDate1(Object value, Object filter, Locale locale) {

    String filterText = (filter == null) ? null : filter.toString().trim();
    if (filterText == null || filterText.equals("")) {
        return true;
    }
    if (value == null) {
        return false;
    }

    DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);

    Date filterDate = (Date) value;
    Date dateFrom;
    Date dateTo;
    try {
        String fromPart = filterText.substring(0, filterText.indexOf("-"));
        String toPart = filterText.substring(filterText.indexOf("-") + 1);
        dateFrom = fromPart.isEmpty() ? null : df.parse(fromPart);
        dateTo = toPart.isEmpty() ? null : df.parse(toPart);
    } catch (ParseException pe) {
        LOG.log(Level.SEVERE, "unable to parse date: " + filterText, pe);
        return false;
    }

    return (dateFrom == null || filterDate.after(dateFrom)) && (dateTo == null || filterDate.before(dateTo));
}

    public boolean filterByDate2(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (value == null) {
            return false;
        }

        DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);

        Date filterDate = (Date) value;
        Date dateFrom;
        Date dateTo;
        try {
            String fromPart = filterText.substring(0, filterText.indexOf("-"));
            String toPart = filterText.substring(filterText.indexOf("-") + 1);
            dateFrom = fromPart.isEmpty() ? null : df.parse(fromPart);
            dateTo = toPart.isEmpty() ? null : df.parse(toPart);
        } catch (ParseException pe) {
            LOG.log(Level.SEVERE, "unable to parse date: " + filterText, pe);
            return false;
        }

        return (dateFrom == null || filterDate.after(dateFrom)) && (dateTo == null || filterDate.before(dateTo));
    }
}
