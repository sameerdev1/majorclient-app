package com.majorgym.client.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majorgym.client.R

private const val DEVELOPER_PHONE = "7702142062"
private const val LINKEDIN_URL =
    "https://www.linkedin.com/in/md-abdul-sameer-447b53391?utm_source=share_via&utm_content=profile&utm_medium=member_android"

/**
 * "About MajorGym" — a simple, in-design-language page reached from the
 * small developer icon on Home. Shows the app blurb, a short developer bio,
 * and tappable contact rows (phone dialer / LinkedIn). Purely local UI —
 * no networking, analytics, or new navigation framework, matching the rest
 * of this app's manual sealed-class navigation.
 */
@Composable
fun AboutScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        containerColor = ClientColors.Background,
        topBar = {
            TopAppBar(
                title = { Text("About MajorGym", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ClientColors.Background,
                    titleContentColor = ClientColors.OnSurface,
                ),
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ClientColors.Background)
                .padding(padding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.dev_profile),
                    contentDescription = "Developer profile photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, ClientColors.Accent.copy(alpha = 0.55f), CircleShape),
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text("Mohammad Abdul Sameer", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Software Developer", color = ClientColors.Hint)

                Spacer(modifier = Modifier.height(24.dp))
                PremiumCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconBadge(Icons.Rounded.FitnessCenter)
                            Column(modifier = Modifier.padding(start = 12.dp)) {
                                Text("MajorGym", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(
                                    "Client Membership & Attendance",
                                    color = ClientColors.Hint,
                                    fontSize = 13.sp,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            "A modern mobile application designed to simplify gym membership and attendance management, providing members with a clean and convenient digital experience.",
                            color = ClientColors.Hint,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                PremiumCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            "Developed by",
                            color = ClientColors.Hint,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Mohammad Abdul Sameer", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Mohammad Abdul Sameer is a Computer Science Engineer with a strong interest " +
                                "in software development, DevOps, cloud technologies, and building practical " +
                                "technology solutions. MajorGym is one of his projects, developed with a focus " +
                                "on creating a simple, modern, and reliable digital experience for gym members.",
                            color = ClientColors.Hint,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                PremiumCard(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        ContactRow(
                            icon = Icons.Rounded.Phone,
                            title = "Phone",
                            subtitle = DEVELOPER_PHONE,
                            contentDescription = "Call developer",
                            onClick = {
                                try {
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$DEVELOPER_PHONE"))
                                    context.startActivity(intent)
                                } catch (e: ActivityNotFoundException) {
                                    // No dialer app available — nothing else to do.
                                }
                            },
                        )
                        InfoDividerAbout()
                        ContactRow(
                            icon = Icons.Rounded.Link,
                            title = "LinkedIn",
                            subtitle = "md-abdul-sameer-447b53391",
                            contentDescription = "Open LinkedIn profile",
                            onClick = {
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(LINKEDIN_URL))
                                    context.startActivity(intent)
                                } catch (e: ActivityNotFoundException) {
                                    // No browser or app available to handle the link.
                                }
                            },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                DeveloperWatermark()
            }
        }
    }
}

@Composable
private fun IconBadge(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(ClientColors.Accent.copy(alpha = 0.14f)),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, contentDescription = null, tint = ClientColors.LightBlue, modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun InfoDividerAbout() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(ClientColors.Divider),
    )
}

@Composable
private fun ContactRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ClientColors.Accent.copy(alpha = 0.14f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                icon,
                contentDescription = contentDescription,
                tint = ClientColors.LightBlue,
                modifier = Modifier.size(20.dp),
            )
        }
        Column(modifier = Modifier.padding(start = 14.dp).weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(subtitle, color = ClientColors.Hint, fontSize = 13.sp)
        }
    }
}
