// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: property.proto

package org.jigsaw.payment;

public final class Property {
  private Property() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_jigsaw_payment_Version_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_jigsaw_payment_Version_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016property.proto\022\022org.jigsaw.payment\032\ndi" +
      "ct.proto\"\236\001\n\007Version\022\017\n\007version\030\001 \002(\t\022\024\n" +
      "\014created_time\030\002 \002(\003\022\017\n\007creator\030\003 \002(\t\022\024\n\014" +
      "creator_name\030\004 \002(\t\022\024\n\014last_updated\030\005 \002(\003" +
      "\022\024\n\014last_updator\030\006 \002(\t\022\031\n\021last_updator_n" +
      "ame\030\007 \002(\tB\002P\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.jigsaw.payment.Dict.getDescriptor(),
        }, assigner);
    internal_static_org_jigsaw_payment_Version_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_jigsaw_payment_Version_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_jigsaw_payment_Version_descriptor,
        new java.lang.String[] { "Version", "CreatedTime", "Creator", "CreatorName", "LastUpdated", "LastUpdator", "LastUpdatorName", });
    org.jigsaw.payment.Dict.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
