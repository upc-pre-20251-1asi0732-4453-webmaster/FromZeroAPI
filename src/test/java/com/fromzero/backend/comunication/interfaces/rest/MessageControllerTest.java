package com.fromzero.backend.comunication.interfaces.rest;

        import com.fromzero.backend.comunication.domain.model.aggregates.Message;
        import com.fromzero.backend.comunication.domain.model.queries.GetAllMessageByRecipientByIdQuery;
        import com.fromzero.backend.comunication.domain.services.MessageCommandService;
        import com.fromzero.backend.comunication.domain.services.MessageQueryService;
        import com.fromzero.backend.comunication.interfaces.rest.resources.CreateMessageResource;
        import com.fromzero.backend.iam.domain.model.aggregates.User;
        import com.fromzero.backend.iam.interfaces.acl.IamContextFacade;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

        import java.time.LocalDate;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.assertNotNull;
        import static org.mockito.Mockito.*;

        class MessageControllerTest {

            @Mock
            private MessageCommandService messageCommandService;

            @Mock
            private MessageQueryService messageQueryService;

            @Mock
            private IamContextFacade iamContextFacade;

            @InjectMocks
            private MessageController messageController;

            private Message message;
            private CreateMessageResource createMessageResource;
            private User sender;
            private User recipient;

            @BeforeEach
            void setUp() {
                MockitoAnnotations.initMocks(this);

                sender = new User();
                sender.setId(1L);
                sender.setUsername("sender.user");

                recipient = new User();
                recipient.setId(2L);
                recipient.setUsername("recipient.user");

                message = new Message();
                message.setId(1L);
                message.setSubject("Test message subject");
                message.setEmailBody("Test message content");
                message.setSender(sender);
                message.setRecipient(recipient);
                message.setSentTime(LocalDate.now());

                createMessageResource = new CreateMessageResource(
                        message.getSubject(),
                        message.getEmailBody(),
                        sender.getId(),
                        recipient.getId()
                );
            }

            @Test
            void createMessage() {
                when(iamContextFacade.getUserById(createMessageResource.senderId())).thenReturn(sender);
                when(iamContextFacade.getUserById(createMessageResource.recipientId())).thenReturn(recipient);
                when(messageCommandService.handle(any())).thenReturn(Optional.of(message));

                assertNotNull(messageController.createMessage(createMessageResource));
            }

            @Test
            void getMessages() {
                when(iamContextFacade.getUserById(recipient.getId())).thenReturn(recipient);
                when(messageQueryService.handle(any(GetAllMessageByRecipientByIdQuery.class)))
                        .thenReturn(List.of(message));

                assertNotNull(messageController.getMessages(recipient.getId()));
            }
        }