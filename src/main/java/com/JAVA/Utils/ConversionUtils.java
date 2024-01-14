package com.JAVA.Utils;

import com.JAVA.Beans.SuiviSymptom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConversionUtils {

    public static List<SuiviSymptom.LocalisationType> convertStringToLocalisations(String[] localisations) {
        return Arrays.stream(localisations)
                .map(SuiviSymptom.LocalisationType::valueOf)
                .collect(Collectors.toList());
    }

    public static List<SuiviSymptom.AggravationType> convertStringToAggravations(String[] aggravations) {
        return Arrays.stream(aggravations)
                .map(SuiviSymptom.AggravationType::valueOf)
                .collect(Collectors.toList());
    }

    public static List<SuiviSymptom.SentimentType> convertStringToSentiments(String[] sentiments) {
        return Arrays.stream(sentiments)
                .map(SuiviSymptom.SentimentType::valueOf)
                .collect(Collectors.toList());
    }

    public static List<SuiviSymptom.SymptomType> convertStringToSymptoms(String[] symptoms) {
        return Arrays.stream(symptoms)
                .map(SuiviSymptom.SymptomType::valueOf)
                .collect(Collectors.toList());
    }
    public static String convertLocalisationsToString(List<SuiviSymptom.LocalisationType> localisations) {
        return convertEnumListToString(localisations);
    }

    public static String convertAggravationsToString(List<SuiviSymptom.AggravationType> aggravations) {
        return convertEnumListToString(aggravations);
    }

    public static String convertSentimentsToString(List<SuiviSymptom.SentimentType> sentiments) {
        return convertEnumListToString(sentiments);
    }

    public static String convertSymptomsToString(List<SuiviSymptom.SymptomType> symptoms) {
        return convertEnumListToString(symptoms);
    }

    private static String convertEnumListToString(List<? extends Enum<?>> enumList) {
        StringBuilder sb = new StringBuilder();
        for (Enum<?> enumValue : enumList) {
            sb.append(enumValue.name()).append(",");
        }
        return removeTrailingComma(sb.toString());
    }

    private static String removeTrailingComma(String input) {
        if (input.endsWith(",")) {
            return input.substring(0, input.length() - 1);
        }
        return input;
    }
    
    

   
}
