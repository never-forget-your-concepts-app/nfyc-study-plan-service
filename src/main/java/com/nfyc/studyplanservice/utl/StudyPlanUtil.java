package com.nfyc.studyplanservice.utl;

import java.util.UUID;

public class StudyPlanUtil {

  final static String UUID_PATTERN="^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

  public static Boolean isValidUUID(UUID uuid) {
      return uuid.toString().matches(UUID_PATTERN);
  }

}
